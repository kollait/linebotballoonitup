/*
 * Copyright 2016 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.linecorp.bot.client;

import static java.util.Collections.emptyList;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import com.linecorp.bot.client.exception.GeneralLineMessagingException;
import com.linecorp.bot.model.Broadcast;
import com.linecorp.bot.model.Multicast;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.profile.MembersIdsResponse;
import com.linecorp.bot.model.profile.UserProfileResponse;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.model.response.GetNumberOfFollowersResponse;
import com.linecorp.bot.model.response.GetNumberOfMessageDeliveriesResponse;
import com.linecorp.bot.model.response.IssueLinkTokenResponse;
import com.linecorp.bot.model.response.MessageQuotaResponse;
import com.linecorp.bot.model.response.NumberOfMessagesResponse;
import com.linecorp.bot.model.response.QuotaConsumptionResponse;
import com.linecorp.bot.model.richmenu.RichMenu;
import com.linecorp.bot.model.richmenu.RichMenuBlukLinkRequest;
import com.linecorp.bot.model.richmenu.RichMenuBlukUnlinkRequest;
import com.linecorp.bot.model.richmenu.RichMenuIdResponse;
import com.linecorp.bot.model.richmenu.RichMenuListResponse;
import com.linecorp.bot.model.richmenu.RichMenuResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Proxy implementation of {@link LineMessagingClient} to hind internal implementation.
 */
@Slf4j
@AllArgsConstructor
public class LineMessagingClientImpl implements LineMessagingClient {
    private static final ExceptionConverter EXCEPTION_CONVERTER = new ExceptionConverter();
    private static final String ORG_TYPE_GROUP = "group"; // TODO Enum
    private static final String ORG_TYPE_ROOM = "room";
    private static final BotApiResponse BOT_API_SUCCESS_RESPONSE = new BotApiResponse("", emptyList());
    private static final Function<Void, BotApiResponse>
            VOID_TO_BOT_API_SUCCESS_RESPONSE = ignored -> BOT_API_SUCCESS_RESPONSE;

    private final LineMessagingService retrofitImpl;

    @Override
    public CompletableFuture<BotApiResponse> replyMessage(final ReplyMessage replyMessage) {
        return toFuture(retrofitImpl.replyMessage(replyMessage));
    }

    @Override
    public CompletableFuture<BotApiResponse> pushMessage(final PushMessage pushMessage) {
        return toFuture(retrofitImpl.pushMessage(pushMessage));
    }

    @Override
    public CompletableFuture<BotApiResponse> multicast(final Multicast multicast) {
        return toFuture(retrofitImpl.multicast(multicast));
    }

    @Override
    public CompletableFuture<BotApiResponse> broadcast(Broadcast broadcast) {
        return toFuture(retrofitImpl.broadcast(broadcast));
    }

    @Override
    public CompletableFuture<MessageContentResponse> getMessageContent(final String messageId) {
        return toMessageContentResponseFuture(retrofitImpl.getMessageContent(messageId));
    }

    @Override
    public CompletableFuture<MessageQuotaResponse> getMessageQuota() {
        return toFuture(retrofitImpl.getMessageQuota());
    }

    @Override
    public CompletableFuture<QuotaConsumptionResponse> getMessageQuotaConsumption() {
        return toFuture(retrofitImpl.getMessageQuotaConsumption());
    }

    @Override
    public CompletableFuture<NumberOfMessagesResponse> getNumberOfSentReplyMessages(String date) {
        return toFuture(retrofitImpl.getNumberOfSentReplyMessages(date));
    }

    @Override
    public CompletableFuture<NumberOfMessagesResponse> getNumberOfSentPushMessages(String date) {
        return toFuture(retrofitImpl.getNumberOfSentPushMessages(date));
    }

    @Override
    public CompletableFuture<NumberOfMessagesResponse> getNumberOfSentMulticastMessages(String date) {
        return toFuture(retrofitImpl.getNumberOfSentMulticastMessages(date));
    }

    @Override
    public CompletableFuture<NumberOfMessagesResponse> getNumberOfSentBroadcastMessages(String date) {
        return toFuture(retrofitImpl.getNumberOfSentBroadcastMessages(date));
    }

    @Override
    public CompletableFuture<UserProfileResponse> getProfile(final String userId) {
        return toFuture(retrofitImpl.getProfile(userId));
    }

    @Override
    public CompletableFuture<UserProfileResponse> getGroupMemberProfile(
            final String groupId, final String userId) {
        return toFuture(retrofitImpl.getMemberProfile(ORG_TYPE_GROUP, groupId, userId));
    }

    @Override
    public CompletableFuture<UserProfileResponse> getRoomMemberProfile(
            final String roomId, final String userId) {
        return toFuture(retrofitImpl.getMemberProfile(ORG_TYPE_ROOM, roomId, userId));
    }

    @Override
    public CompletableFuture<MembersIdsResponse> getGroupMembersIds(
            final String groupId, final String start) {
        return toFuture(retrofitImpl.getMembersIds(ORG_TYPE_GROUP, groupId, start));
    }

    @Override
    public CompletableFuture<MembersIdsResponse> getRoomMembersIds(
            final String roomId, final String start) {
        return toFuture(retrofitImpl.getMembersIds(ORG_TYPE_ROOM, roomId, start));
    }

    @Override
    public CompletableFuture<BotApiResponse> leaveGroup(final String groupId) {
        return toFuture(retrofitImpl.leaveGroup(groupId));
    }

    @Override
    public CompletableFuture<BotApiResponse> leaveRoom(final String roomId) {
        return toFuture(retrofitImpl.leaveRoom(roomId));
    }

    @Override
    public CompletableFuture<RichMenuResponse> getRichMenu(final String richMenuId) {
        return toFuture(retrofitImpl.getRichMenu(richMenuId));
    }

    @Override
    public CompletableFuture<RichMenuIdResponse> createRichMenu(final RichMenu richMenu) {
        return toFuture(retrofitImpl.createRichMenu(richMenu));
    }

    @Override
    public CompletableFuture<BotApiResponse> deleteRichMenu(final String richMenuId) {
        return toBotApiFuture(retrofitImpl.deleteRichMenu(richMenuId));
    }

    @Override
    public CompletableFuture<RichMenuIdResponse> getRichMenuIdOfUser(final String userId) {
        return toFuture(retrofitImpl.getRichMenuIdOfUser(userId));
    }

    @Override
    public CompletableFuture<BotApiResponse> linkRichMenuIdToUser(
            final String userId, final String richMenuId) {
        return toBotApiFuture(retrofitImpl.linkRichMenuToUser(userId, richMenuId));
    }

    @Override
    public CompletableFuture<BotApiResponse> linkRichMenuIdToUsers(List<String> userIds, String richMenuId) {
        return toBotApiFuture(retrofitImpl.linkRichMenuToUsers(RichMenuBlukLinkRequest.builder()
                                                                                      .richMenuId(richMenuId)
                                                                                      .userIds(userIds)
                                                                                      .build()));
    }

    @Override
    public CompletableFuture<BotApiResponse> unlinkRichMenuIdFromUser(final String userId) {
        return toBotApiFuture(retrofitImpl.unlinkRichMenuIdFromUser(userId));
    }

    @Override
    public CompletableFuture<BotApiResponse> unlinkRichMenuIdFromUsers(List<String> userIds) {
        return toBotApiFuture(retrofitImpl.unlinkRichMenuIdFromUsers(RichMenuBlukUnlinkRequest.builder()
                                                                                              .userIds(userIds)
                                                                                              .build()));
    }

    @Override
    public CompletableFuture<MessageContentResponse> getRichMenuImage(final String richMenuId) {
        return toMessageContentResponseFuture(retrofitImpl.getRichMenuImage(richMenuId));
    }

    @Override
    public CompletableFuture<BotApiResponse> setRichMenuImage(
            final String richMenuId, final String contentType, final byte[] content) {
        final RequestBody requestBody = RequestBody.create(MediaType.parse(contentType), content);
        return toBotApiFuture(retrofitImpl.uploadRichMenuImage(richMenuId, requestBody));
    }

    @Override
    public CompletableFuture<RichMenuListResponse> getRichMenuList() {
        return toFuture(retrofitImpl.getRichMenuList());
    }

    @Override
    public CompletableFuture<BotApiResponse> setDefaultRichMenu(final String richMenuId) {
        return toBotApiFuture(retrofitImpl.setDefaultRichMenu(richMenuId));
    }

    @Override
    public CompletableFuture<RichMenuIdResponse> getDefaultRichMenuId() {
        return toFuture(retrofitImpl.getDefaultRichMenuId());
    }

    @Override
    public CompletableFuture<BotApiResponse> cancelDefaultRichMenu() {
        return toBotApiFuture(retrofitImpl.cancelDefaultRichMenu());
    }

    @Override
    public CompletableFuture<IssueLinkTokenResponse> issueLinkToken(String userId) {
        return toFuture(retrofitImpl.issueLinkToken(userId));
    }

    @Override
    public CompletableFuture<GetNumberOfMessageDeliveriesResponse> getNumberOfMessageDeliveries(String date) {
        return toFuture(retrofitImpl.getNumberOfMessageDeliveries(date));
    }

    @Override
    public CompletableFuture<GetNumberOfFollowersResponse> getNumberOfFollowersResponse(String date) {
        return toFuture(retrofitImpl.getNumberOfFollowers(date));
    }

    // TODO: Extract this method.
    static <T> CompletableFuture<T> toFuture(Call<T> callToWrap) {
        final CallbackAdaptor<T> completableFuture = new CallbackAdaptor<>();
        callToWrap.enqueue(completableFuture);
        return completableFuture;
    }

    private static CompletableFuture<BotApiResponse> toBotApiFuture(Call<Void> callToWrap) {
        final CallbackAdaptor<Void> completableFuture = new CallbackAdaptor<>();
        callToWrap.enqueue(completableFuture);
        return completableFuture.thenApply(VOID_TO_BOT_API_SUCCESS_RESPONSE);
    }

    private static CompletableFuture<MessageContentResponse> toMessageContentResponseFuture(
            final Call<ResponseBody> callToWrap) {
        final ResponseBodyCallbackAdaptor future = new ResponseBodyCallbackAdaptor();
        callToWrap.enqueue(future);
        return future;
    }

    static class CallbackAdaptor<T> extends CompletableFuture<T> implements Callback<T> {
        @Override
        public void onResponse(final Call<T> call, final Response<T> response) {
            if (response.isSuccessful()) {
                complete(response.body());
            } else {
                completeExceptionally(EXCEPTION_CONVERTER.apply(response));
            }
        }

        @Override
        public void onFailure(final Call<T> call, final Throwable t) {
            completeExceptionally(
                    new GeneralLineMessagingException(t.getMessage(), null, t));
        }
    }

    static class ResponseBodyCallbackAdaptor
            extends CompletableFuture<MessageContentResponse>
            implements Callback<ResponseBody> {

        @Override
        public void onResponse(final Call<ResponseBody> call, final Response<ResponseBody> response) {
            if (!response.isSuccessful()) {
                completeExceptionally(EXCEPTION_CONVERTER.apply(response));
                return;
            }

            try {
                complete(convert(response));
            } catch (RuntimeException exceptionInConvert) {
                completeExceptionally(
                        new GeneralLineMessagingException(exceptionInConvert.getMessage(),
                                                          null, exceptionInConvert));
            }
        }

        @Override
        public void onFailure(final Call<ResponseBody> call, final Throwable t) {
            completeExceptionally(
                    new GeneralLineMessagingException(t.getMessage(), null, t));
        }

        private MessageContentResponse convert(final Response<ResponseBody> response) {
            return MessageContentResponse
                    .builder()
                    .length(response.body().contentLength())
                    .allHeaders(response.headers().toMultimap())
                    .mimeType(response.body().contentType().toString())
                    .stream(response.body().byteStream())
                    .build();
        }
    }
}
